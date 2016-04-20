package ru.ncedu.java.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tumas on 17.04.2016.
 */
public class CheckerImpl implements Checker{
    private String inputString;
    private Pattern pattern;
    private Matcher matcher;

    @Override public Pattern getPLSQLNamesPattern() {
        return Pattern.compile("^[A-Za-z]{1}[A-Za-z0-9_$]{0,29}");
    }
    @Override public Pattern getEMailPattern() {
        return Pattern.compile("^([a-zA-Z0-9][a-zA-Z0-9_.\\-]{0,20}[a-zA-Z0-9]|[a-zA-Z0-9])[@]([a-zA-Z0-9]{1}[a-zA-Z0-9\\-]*[a-zA-Z0-9]{1}\\.)+(ru|com|net|org)$");
    }
    @Override public boolean checkAccordance(String inputString, Pattern pattern) throws IllegalArgumentException {
        if((inputString==null&&pattern!=null)||(inputString!=null&&pattern==null)){
            throw new IllegalArgumentException();
        }else if(inputString==null&&pattern==null){
            return true;
        }else{
            if(pattern.matcher(inputString).matches())
                return true;
            else return false;
        }
    }
    @Override public List<String> fetchAllTemplates(StringBuffer inputString, Pattern pattern) throws IllegalArgumentException {
        List<String> result = new ArrayList<>();
        Matcher m = pattern.matcher(inputString);
        while (m.find()) {
            result.add(m.group(0));
        }
        return result;
    }

    /**
     * Содержащиеся на web-странице URLы описываются тегом &lt;a href = ...&gt; (или &lt;a href=.../&gt;).<br/>
     * Ремарка для начинающих: в HTML &gt; - это > (больше), &lt; - это < (меньше), а комментарии пишутся в таком
     * "странном" виде, чтобы они корректно отображались в HTML, который из них генерируется через javadoc).<br/>
     * То есть, следует читать: URLы описываются тегом <a href = ...> (или <a href=.../>).<br/>
     * Будем условно называть URLом закрытый или незакрытый тег a с обязательным атрибутом href,
     * значение которого не должно содержать пробельных символов (см.ниже). <br/>
     * Заключать значение атрибута href в кавычки необязательно, но если использованы двойные кавычки,
     * то в значении МОГУТ быть пробельные символы.<br/>
     * Имена тега A и атрибута HREF (как и другие имена в HTML) не чувствительны к регистру.<br/>
     * Между символом меньше, именем тега, названием атрибута, знаком равно и символом больше
     * могут быть следующие пробельные символы:
     * табуляция, перевод строки, возврат каретки, перевод формата, пробел.<br/>
     *
     * @return шаблон для выделения содержащихся на web-странице URL-ов.
     */ @Override public Pattern getHrefURLPattern() {
        return Pattern.compile("<[\\s]*[Aa][\\s]+[H|h][R|r][E|e][F|f][\\s]*=[\\s]*(([^\\s\"]*)|(\"([^\"{0}]*)\"))[\\s]*[/]?>");
    }

}