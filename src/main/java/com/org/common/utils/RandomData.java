package com.org.common.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RandomData
{
    public String getCurrentTimeStamp()
    {
        String timeStamp = getCurrentTimeStampRaw();
        timeStamp = timeStamp.replace(":", "-").replace(".", "-").replace(" ", "_").trim();
        return timeStamp;
    }

    public String getBookingTime() {
        @SuppressWarnings("serial")
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ") {
            @Override
            public StringBuffer format(Date date, StringBuffer toAppendTo, java.text.FieldPosition pos) {
                StringBuffer toFix = super.format(date, toAppendTo, pos);
                return toFix.insert(toFix.length() - 2, ':');
            }
        };

        date.setTimeZone(TimeZone.getTimeZone("GMT"));
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, 1);
        Date d = c.getTime();
        String time = date.format(d);
        return time;
    }

    public String getCurrentTimeStampRaw()
    {
        Date date = new Date();
        String timeStamp = (new Timestamp(date.getTime())).toString();
        return timeStamp;
    }

    public String getRandomString(int length)
    {
        Random rand = new Random();
        String result = new String();
        for(int i=0; i<length; i++)
        {
            char c = (char)(rand.nextInt(26) + 'A');
            result += c;
        }
        return result;
    }

    public String getRandomInteger(int length)
    {
        Random rand = new Random();
        String result = new String();
        for(int i=0; i<length; i++)
        {
            result += rand.nextInt(9);
        }
        return result;
    }
    public String getResolvedData(String source)
    {
        if(source.contains("{STRING:"))
        {
            //String source = "fjs{STRING:31}DJD{STRING:8}";
            Pattern p = Pattern.compile("\\{STRING:[0-99999]*}");   // the pattern to search for
            Matcher m = p.matcher(source);

            String output = "";
            int startIndex = 0, end = 0;
            while(m.find())
            {
                end = m.start();
                output = output + source.substring(startIndex, end);

                String group = m.group();
                //System.out.println(group);
                output += formatString(group);
                int length = group.length();
                startIndex = end + length;
                //System.out.println(startIndex);
            }

            if(source.length() > startIndex)
            {
                output = output + source.substring(startIndex);
            }

            //System.out.println(output);
            return output;
        }
        else
        {
            return source;
        }
    }

    private String formatString(String input)
    {
        input = input.trim().replace("{", "").replace("}", "");
        String []str = input.split(":");
        int length = Integer.parseInt(str[1]);
        String output = "";
        switch(str[0])
        {
            case "STRING": output = getRandomString(length);
                break;
            case "INT:": output = getRandomInteger(length);
                break;
        }
        return output;
    }
}

