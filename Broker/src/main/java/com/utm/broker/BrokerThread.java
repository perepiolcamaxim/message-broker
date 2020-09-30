//package com.utm.broker;
//
//import com.google.gson.Gson;
//import com.utm.broker.services.PublisherService;
//import com.utm.common.rcp.publisher.Payload;
//import sun.jvm.hotspot.code.Stub;
//
//import java.io.*;
//import java.net.Socket;
//
//public class BrokerThread implements Runnable {
//    private PublisherService publisherService;
//    private final PayloadHandler handler;
//    private PrintWriter writer = null;
//    private BufferedReader reader = null;
//
//    public BrokerThread(Stub clientStub) {
//        this.clientStub = clientStub;
//        //       try
////        {
////            writer = new PrintWriter(clientSocket.getOutputStream());
////            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
////        }
////        catch (IOException e)
////        {
////            System.out.println("Can't open stream!");
////        }
//        handler = new PayloadHandler();
//    }
//
//    @Override
//    public void run() {
//        try {
//            String inputLine;
//            int outputLine;
//
//            while ((inputLine = reader.readLine()) != null) {
//                Gson gson = new Gson();
//                Payload payload = gson.fromJson(inputLine, Payload.class);
//
//                handler.handle(clientStub, payload);
//            }
//        } catch (InterruptedIOException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            System.out.println("Client disconected");
////            try {
////                writer.close();
////                reader.close();
////                ConnectionStorage.remove();
////                clientSocket.close();
////            } catch (IOException ioException) {
////                ioException.printStackTrace();
////                System.out.println("Can't clone the socket!");
////            }
//        }
//    }
//}
