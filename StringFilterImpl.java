package ru.ncedu.java.tasks;

import java.util.*;


public class StringFilterImpl implements StringFilter {

    private Collection<String> strings = new HashSet<>();


    /**
     * Очищает набор - удаляет из него все строки
     */
    @Override
    public void removeAll() {
        strings.removeAll(strings);
    }

    /**
     * Возвращает набор (коллекцию), в котором хранятся строки.
     * В наборе не может быть двух одинаковых строк, однако может быть null.
     */
    @Override
    public Collection<String> getCollection() {
        return this.strings;
    }

    /**
     * Добавляет строку s в набор, приводя ее к нижнему регистру.
     * Если строка s уже есть в наборе, ничего не делает.
     *
     * @param s может быть null
     */
    @Override
    public void add(String s) {
        String tmp = null;
        if (s != null) { //@param s может быть null
            tmp = s.toLowerCase();
        }
        if (strings.contains(tmp)) {//Если строка s уже есть в наборе, ничего не делает.
            return;
        }
        strings.add(tmp); //Добавляет строку s в набор, приводя ее к нижнему регистру.
    }

    /**
     * Удаляет строку s из набора (предварительно приведя ее к нижнему регистру).
     *
     * @param s может быть null
     * @return true если строка была удалена, false если строка отсутствовала в наборе.
     */
    @Override
    public boolean remove(String s) {
        if (strings.contains(s)) {
            strings.remove(s);
            return true;
        } else return false;
    }

    /**
     * Ищет и возвращает все строки, содержащие указанную последовательность символов.<br/>
     * Если <code>chars</code> - пустая строка или <code>null</code>,
     * то результат содержит все строки данного набора.<br/>
     *
     * @param chars символы, входящие в искомые строки
     *              (все символы, являющиеся буквами, - в нижнем регистре)
     * @return строки, содержащие указанную последовательность символов
     */
    @Override
    public Iterator<String> getStringsContaining(String chars) {

        Collection<String> collection = new HashSet<>();
        if (chars == null) {
            return strings.iterator();
        } else if (chars.equals("")) {
            return strings.iterator();
        } else {
            for (String string : strings) {
                if (string != null && string.toLowerCase().contains(chars)) {
                    collection.add(string);
                }
            }
            return collection.iterator();
        }

    }

    /**
     * Ищет и возвращает строки, начинающиеся с указанной последовательности символов,
     * (без учета регистра). <br/>
     * Если <code>begin</code> - пустая строка или <code>null</code>,
     * то результат содержит все строки данного набора.<br/>
     *
     * @param begin первые символы искомых строк
     *              (для сравнения со строками набора символы нужно привести к нижнему регистру)
     * @return строки, начинающиеся с указанной последовательности символов
     */
    @Override
    public Iterator<String> getStringsStartingWith(String begin) {
        Collection<String> collection = new HashSet<>();
        if (begin != null && !begin.equals("")) {
            for (String string : strings) {
                if (null != string && string.toLowerCase().startsWith(begin.toLowerCase())) {
                    collection.add(string);
                }
            }
            return collection.iterator();
        } else return strings.iterator();
    }

    /**
     * Ищет и возвращает строки, удовлетворяющие заданному шаблону поиска, содержащему символы *
     * в качестве wildcards (на месте * в строке может быть ноль или больше любых символов).<br/>
     * <a href="http://en.wikipedia.org/wiki/Wildcard_character#File_and_directory_patterns">Про * wildcard</a>.<br/>
     * Примеры шаблонов, которым удовлетворяет строка "distribute": "distr*", "*str*", "di*bute*".<br/>
     * Упрощение: достаточно поддерживать всего два символа * в шаблоне.<br/>
     * Примечание: в данной постановке задачи НЕ предполагается использование регулярных выражений
     * и какого-либо высокоуровневого API (эти темы изучаются позже), цель - применить методы String.<br/>
     * Если <code>pattern</code> - пустая строка или <code>null</code>,
     * то результат содержит все строки данного набора.<br/>
     *
     * @param pattern шаблон поиска (все буквы в нем - в нижнем регистре)
     * @return строки, удовлетворяющие заданному шаблону поиска
     */
    @Override
    public Iterator<String> getStringsByPattern(String pattern) {
        Collection<String> collection = new HashSet<>();
        //если передаваемый паттерне null или пустой
        if (countOfStarsInPattern(pattern) == 0) {
            return strings.iterator();
        }
        //если паттерн соделжит одну звездочку
        if (countOfStarsInPattern(pattern) == 1) {
            //*str
            if (pattern.indexOf("*") == 0) {
                for (String str : strings) {
                    if (str != null && str.endsWith(pattern.substring(1))) {
                        collection.add(str);
                    }
                }
                //str*
            } else if (pattern.indexOf("*") == pattern.length() - 1) {
                for (String str : strings) {
                    if (str != null && str.startsWith(pattern.substring(0, pattern.length() - 1))) {
                        collection.add(str);
                    }
                }
                //str*str
            } else {
                for (String str : strings) {
                    if (str != null && str.startsWith(pattern.substring(0, pattern.indexOf("*"))) &&
                            str.endsWith(pattern.substring(pattern.indexOf("*") + 1)) &&
                            str.length() >= pattern.length() - 1) {
                        collection.add(str);
                    }
                }
            }


            //если петтерн содержит две звездочки
        } else {
            //*str*
            if (pattern.indexOf("*") == 0 && pattern.lastIndexOf("*") == pattern.length() - 1) {
                for (String str : strings) {
                    if (str != null && str.contains(pattern.substring(1, pattern.length() - 1))) {
                        collection.add(str);
                    }
                }
                //str*str*
            } else if (pattern.lastIndexOf("*") == pattern.length() - 1 && pattern.indexOf("*") != 0) {
                for (String str : strings) {
                    if (str != null && str.startsWith(pattern.substring(0, pattern.indexOf("*") - 1)) &&
                            str.contains(pattern.substring(pattern.indexOf("*") + 1, pattern.length() - 1)) &&
                            str.length() >= pattern.length() - 2) {
                        collection.add(str);
                    }
                }
                //*str*str
            } else {
                for (String str : strings) {
                    if (str != null && str.contains(pattern.substring(1, pattern.lastIndexOf("*") - 1)) &&
                            str.endsWith(pattern.substring(pattern.lastIndexOf("*") + 2)) &&
                            str.length() > pattern.length() - 2) {
                        collection.add(str);
                    }
                }
            }
        }
        return collection.iterator();
    }

    private int countOfStarsInPattern(String pattern) {

        int countStars = 0;
        if (pattern == null) {
            return countStars;
        } else if (pattern.equals("")) {
            return countStars;
        } else {
            char[] patterAsArrayOfChars = pattern.toCharArray();
            for (int i = 0; i < patterAsArrayOfChars.length; i++) {
                if (patterAsArrayOfChars[i] == '*') {
                    countStars++;
                }
            }
        }
        return countStars;
    }

    /**
     * Ищет и возвращает все строки, представляющие собой число в заданном формате.<br/>
     * Формат может содержать символ # (место для одной цифры от 0 до 9) и любые символы.
     * Примеры форматов: "(###)###-####" (телефон), "# ###" (целое число от 1000 до 9999),
     * "-#.##" (отрицательное число, большее -10, с ровно двумя знаками после десятичной точки).<br/>
     * Упрощающее ограничение: в строке, удовлетворяющей формату, должно быть ровно столько символов,
     * сколько в формате (в отличие от стандартного понимания числового формата,
     * где некоторые цифры на месте # не являются обязательными).<br/>
     * Примечание: в данной постановке задачи НЕ предполагается использование регулярных выражений
     * или какого-либо высокоуровневого API (эти темы изучаются позже).<br/>
     * Если <code>format</code> - пустая строка или <code>null</code>,
     * то результат содержит все строки данного набора.<br/>
     *
     * @param format формат числа
     * @return строки, удовлетворяющие заданному числовому формату
     */


    @Override
    public Iterator<String> getStringsByNumberFormat(String format) {
        Collection<String> answer = new HashSet<>();

        char[] s;
        String numbers = "1234567890";
        if (format != null && !format.equals("")) {
            char[] f = format.toCharArray();
            for (String str : strings) {
                if (str != null) {
                    s = str.toCharArray();
                    //на данный момент строка и формат превратились в символьные массивы, проверим равна ли их длина
                    if (s.length == f.length) {
                        //пройдём по длине и сравним элементы, которые эквивалентны
                        int countNotResh=0;
                        int countSimular=0;
                        //в следующих двух блоках мы находим колличество символов не равных решетке и кол-во символов не раных решетки и равных строке
                        for (int i = 0; i < f.length; i++) {
                            if (f[i] != '#' ){
                                countNotResh++;
                            }
                            if(f[i]!='#'&& (f[i]==s[i])){
                                countSimular++;
                            }
                        }
                        int countResh=0;
                        int countNumber=0;
                        //если совпало кол-во похожих и кол-во нерешеток, то выолняем проверку на цифры
                        if(countNotResh==countSimular){
                            for (int i = 0; i < f.length; i++) {
                                if (f[i] == '#' ){
                                    countResh++;
                                }
                                if(f[i]=='#'&&numbers.contains(s[i]+"")){
                                    countNumber++;
                                }
                            }
                            if(countResh==countNumber){
                                answer.add(str);
                            }
                        }
                    }

                }
            }
            return answer.iterator();
        }else return strings.iterator();

    }


}
