/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.oilukraine.server;

/**
 * Класс потока, для обработки входящих соединений.
 *
 * @author u_gorbonos
 */
public class Worker extends Thread {

//    Socket clientTCP;
  //  TCPClient clientTCP;

    /**
     * Конструктор, принимает параметром сокет соединенный с клиентом дальше
     * устанавливает связь с сервером и просто ждет уведомления о необходимости
     * нотификации клиента
     *
     * @param cl
     */
    public Worker(){
//        TCPClient cl) {
//        this.clientTCP = cl;
        System.out.println("Client - Создали поток" + this.toString());
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Client - Удаляем поток" + this.toString());
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public void interrupt() {
//        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
//    }
    @Override
    public void interrupt() {
        System.out.println("Client - нас прерывают " + this.toString());
        super.interrupt();
//        clientTCP.close();
//        clientTCP = null;
    }

    /**
     * Рабочий метод класса. В нем устанавливается соединение с сервером и ждем
     * уведомлений
     */
    @Override
    public void run() {
        System.out.println("Client - Инициализируемся " + this.toString());
//        clientTCP.init();
//        System.out.println("Client - Ждем информацию от сервера " + this.toString());
//        while (true && !this.isInterrupted()) {
//            if (clientTCP != null) {
//                System.out.println("Client - clientTCP.connect() " + this.toString());
//                clientTCP.connect();
//            }
//        }
        System.out.println("Client - Дождались информацию от сервера " + this.toString());
    }

//    /**
//     * Уведомляем клиента о необходимости обновить данные.
//     * Если Происходит ошибка, передаем её в вызывающую функцию и обрабатываем там.
//     * @throws IOException
//     */
//    public void AlertClient() throws IOException {
//        BufferedOutputStream outToClient;
//        outToClient = new BufferedOutputStream(this.clientTCP.getOutputStream());
//        outToClient.write(100);
//    }
}
