
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//второй вариант программы по составлению дел
public class Main {
    //регулярное выражение индекса
    public static final String INDEX_REGEX = "^\\d+";
    //регулярное выражение индекса и дела
    public static  final String INDEX_AND_TODO_REGEX="^(\\d+)(\\s+)(.+)";
    //создаем пустой список дел
    private static List <String> list =new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("\t\tДобро пожаловать в программу по составлению списка дел!");
        //инфо отображает доступные команды
        String info = "Доступные команды:\n" +
                "Добавить {дело}\n" +
                "Добавить {индекс} {дело}\n" +
                "Удалить {индекс}\n" +
                "Изменить {индекс} {дело}\n" +
                "Печать\n" +
                "Инфо\n" +
                "Выход\n";
        System.out.println(info+ "\n");
        while(true){
            System.out.println("Введите команду:");
            //ввод данных
            String input = new Scanner(System.in).nextLine();
            //сохраняем ввод в переменную command в случае, если команда состоит из одного слова
            //как например Печать или Выход
            String command = input;
            //полезная нагрузка - все что находится после команды
            String payload = "";
            //если ввод содержит несколько слов, разделяем их в массив через пробелы
            if(input.contains(" ")) {
                String[] lexemes = input.split("\\s+", 2);
                //наши команды
                command = lexemes[0];
                //все что идет после команд записывается как один элемент массива
                payload = lexemes[1].trim();
            }
            //выход из программы
            if("выход".equalsIgnoreCase(command)){
                return;
            }
            //добавляем дела независимо от регистров
            if(command.equalsIgnoreCase("Добавить")){
                //если в деле после команды есть число, то извлекаем его и записываем в индекс
                //также делаем и с самим делом
                if(payload.matches(INDEX_AND_TODO_REGEX)){
                    Integer index = Integer.parseInt(payload.replaceAll(INDEX_AND_TODO_REGEX,"$1").trim());
                    String todo = payload.replaceAll(INDEX_REGEX,"").trim();
                    //используем метод, чтобы добавить дело через индекс
                    add(todo,index-1);
                }else{
                    //добавляем дело без индекса
                    add(payload);
                }
            }
            //удаляем дело с указанием индекса
            else if(command.equalsIgnoreCase("Удалить")){
                Integer index = Integer.parseInt(payload);
                delete(index-1);
            }
            //изменяем дело с указанием индекса
            else if(command.equalsIgnoreCase("Изменить")){
                Integer index = Integer.parseInt(payload.replaceAll(INDEX_AND_TODO_REGEX,"$1").trim());
                String todo = payload.replaceAll(INDEX_REGEX,"").trim();
                edit(todo,index-1);
            }
            //выводим список дел
            else if(command.equalsIgnoreCase("Печать")){
                print();
            }
            else{
                System.out.println("Ввод неверен!\n"+info);
            }
        }
    }
    //перегруженный метод добавления дел без индекса
    public static void add(String todo){
        list.add(todo);
        System.out.println("Дело \""+todo+"\" добавлено");
    }
    //перегруженный метод добавления дел через индекс
    public static void add(String todo, Integer index){
        if(index<0 || index>= list.size()){
            list.add(todo);
            System.out.println("На "+(index+1)+" индексе нет места. Дело добавлено в конец списка.");
        }
        else{
            list.add(index, todo);
            System.out.println("На "+(index+1)+" место добавлено дело \""+todo+"\"");
        }
    }
    //метод удаления дел через индекс
    public static void delete(Integer index){
        if(index<0 || index>=list.size()){
            System.out.println("Дела под индексом "+(index+1)+" нет в списке.");
        }
        else{
            String todo = list.get(index);
            list.remove(todo);
            System.out.println("Дело \""+todo+"\" удалено");
        }
    }
    //метод изменения дел через индекс
    public static void edit(String newTodo, Integer index){
        if(index<0 || index>=list.size()){
            System.out.println("Дела под индексом "+(index+1)+" нет в списке.");
        }
        else{
            String oldTodo = list.get(index);
            list.set(index, newTodo);
            System.out.println("Дело \""+oldTodo+"\" заменено на \""+newTodo+"\".");
        }
    }
    //метод печати списка дел
    public static void print(){
        if(list.isEmpty()){
            System.out.println("Список дел пустой");
            return;
        }
        for(String printList:list){
            System.out.println(printList);
        }
    }


}