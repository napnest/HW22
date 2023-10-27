
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//примитивная программа todos
public class Main {
    //создаем лист с todos действиями
    static List<String> list = new ArrayList<>();
    public static void main(String[] args) {
        //используем Scanner для ввода с консоли
        Scanner scanner = new Scanner(System.in);
        //в цикле while записываем логику программы
        while (true) {
            //вводим действия
            String task = scanner.nextLine();
            //выход из цикла
            if("выход".equalsIgnoreCase(task)){
                break;
            }
            //разделяем полученную строку на элементы массива
            String [] command = task.split("\\s+");
            //определяем переменную для извлечения из строки числа
            int number;
            //первое слово - указание программе, какие инструкции выполнить
            switch (command[0]) {
                //добавляем действие в лист
                case "Добавить":
                    //если второй элемент число, то используем его для добавления элемента по индексу
                    if (command[1].matches("\\d+")){
                        //временная переменная для извлечения числа
                        String temp = task.replaceAll("[А-яЁё\\s]+"," ").trim();
                        //преобразуем строку в массив
                        String[] split= temp.split("\\s");
                        //используем первое число как индекс
                        int position = Integer.parseInt(split[0]);
                        //условие, если индекс за пределами длины листа, то добавляем действие в конец списка
                        if (position<0 || position>list.size()){
                            task = task.replaceAll("^[А-яЁё]+\\s+\\d+", "").trim();
                            list.add(task);
                            System.out.println("Нет места под номером "+position+". Дело "+task+" добавлено в конец списка.");
                            break;
                        }
                        //добавляем действие в указанный диапазон
                        task = task.replaceAll("^[А-яЁё]+\\s+\\d+", "").trim();
                        list.add(position-1,task);
                        System.out.println("На "+ position +" место"+" добавлено дело: "+task);
                        break;
                    }
                    //добавляем действие в конец листа
                    task = task.replaceAll("^[А-яЁё]+\\s+", "").trim();
                    list.add(task);
                    System.out.println("Добавлено дело: "+task);
                    break;
                //инструкция - вывод на печать элементов листа
                case "Печать":
                    for (String i : list) {
                        System.out.println("Дело: " + i);
                    }
                    break;
                //инструкция - удаление элемента из листа
                case "Удалить":
                    //преобразуем из строки в число, данное число индекс для удаления
                    number = Integer.parseInt(command[1]);
                    if(number<0 || number>list.size()){
                        System.out.println("Данного дела нет");
                        break;
                    }
                    System.out.println("Дело : "+list.get(number-1)+" удалено");
                    list.remove(number-1);
                    break;
                //инструкция - изменение элемента листа
                case "Изменить":
                    //данное число индекс для изменения элемента
                    number = Integer.parseInt(command[1]);
                    //изменяем строку
                    task = task.replaceAll("^[А-яЁё]+\\s+\\d+\\s+", "").trim();
                    System.out.print("Дело: "+list.get(number-1)+" ");
                    list.set(number-1, task);
                    System.out.println("изменено на "+ list.get(number-1));
                    break;
                //неправильный ввод
                default:
                    System.out.println("Неправильный ввод, повторите еще раз");
                    break;
            }
        }


    }

}