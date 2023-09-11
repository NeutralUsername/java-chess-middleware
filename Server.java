import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;
import java.util.HashMap;

import chess.Chess;

public class Server {
    private static Server singleton;
    private HashMap<String, Connection> connections;
    private ServerSocket serverSocket;
    private ConnectionListener connectionListener = new ConnectionListener(this);

    public static Server getSingleton() {
        if (singleton == null) {
            singleton = new Server(4711);
        }
        return singleton;
    }

    private Server(int port) {
        connections = new HashMap<String, Connection>();
        try {
            serverSocket = new ServerSocket(port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleMessage(Connection senderConnection, String messageType, String messageContent) {
        switch (messageType) {
            case "c":
                Connection receiverConnection = getConnection(messageContent);
                if (receiverConnection == null) {
                    senderConnection.sendMessage("e", "unknown id");
                } else if (senderConnection == receiverConnection) {
                    senderConnection.sendMessage("e", "invalid id");
                } else if (receiverConnection.isIngame() || senderConnection.isIngame()) {
                    senderConnection.sendMessage("e", "already in game");
                } else {
                    Chess game = new Chess();
                    senderConnection.setGame(game, true);
                    receiverConnection.setGame(game, false);
                    senderConnection.setOpponent(receiverConnection);
                    receiverConnection.setOpponent(senderConnection);
                    senderConnection.sendMessage("w", game.getBoardString());
                    receiverConnection.sendMessage("b", game.getBoardString());
                }
                break;
            case "m":
                String[] split = messageContent.split(",");
                int from = Integer.parseInt(split[0]);
                int to = Integer.parseInt(split[1]);
                int fromRow = from / 8;
                int fromCol = from % 8;
                int toRow = to / 8;
                int toCol = to % 8;
                Chess game = senderConnection.getGame();
                if (game == null) {
                    senderConnection.sendMessage("e", "not in game");
                } else if (game.isWhiteTurn() != senderConnection.isWhite()) {
                    senderConnection.sendMessage("e", "not your turn");
                } else if (!game.isValidAction(fromRow, fromCol, toRow, toCol)) {
                    senderConnection.sendMessage("e", "invalid move");
                } else {
                    game.move(fromRow, fromCol, toRow, toCol);
                    senderConnection.sendMessage(senderConnection.isWhite() ? "w" : "b", game.getBoardString());
                    if (!senderConnection.getOpponent().isClosed()) {
                        senderConnection.getOpponent().sendMessage(senderConnection.isWhite() ? "b" : "w",
                                game.getBoardString());
                    }
                }
                break;
            case "x":
                String movesString = senderConnection.getGame().getMovesString();
                File file = new File(senderConnection.getId() + "_" + senderConnection.getOpponent().getId() + "_"
                        + Time.valueOf(java.time.LocalTime.now()).toString() + ".log");
                try {
                    file.createNewFile();
                    FileWriter writer = new FileWriter(file);
                    writer.write(movesString);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                senderConnection.sendMessage("x", "");
                if (!senderConnection.getOpponent().isClosed()) {
                    senderConnection.getOpponent().sendMessage("x", "");
                }
                senderConnection.getOpponent().setGame(null, null);
                senderConnection.getOpponent().setOpponent(null);
                senderConnection.setGame(null, null);
                senderConnection.setOpponent(null);
                break;

        }
    }

    public boolean startConnectionListener() {
        if (connectionListener.isRunning()) {
            return false;
        }
        new Thread(connectionListener).start();
        return true;
    }

    public boolean stopConnectionListener() {
        if (!connectionListener.isRunning()) {
            return false;
        }
        connectionListener.stop();
        return true;
    }

    public Socket acceptNextSocket() {
        try {
            Socket socket = serverSocket.accept();
            return socket;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void terminateServer() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addConnection(Connection connection) {
        connections.put(connection.getId(), connection);
    }

    public void removeConnection(String id) {
        connections.remove(id);
    }

    public Connection getConnection(String id) {
        return connections.get(id);
    }
}
