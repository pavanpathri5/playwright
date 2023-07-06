package com.org.common.utils;

import io.qameta.allure.Allure;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger
{

    public static String getTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
    private static final int _MAX_VIEW = 200;
    public enum LogType
    {
        ERROR, INFO, EXCEPTION
    }

    public static void log(String message, LogType type)
    {
        try
        {
            message = type.name() + " : " + getTime() + ": " + message;

            String shortMsg = message;
            if(message.length() > _MAX_VIEW)
                shortMsg = message.substring(0, _MAX_VIEW - 1) + "...";
            System.out.println("<Logging> => " + shortMsg);
            Allure.step(shortMsg);
        }
        catch(Exception ex)
        {
            System.out.println("ERROR DURING LOGGING");
            System.out.println(ex.toString());
        }
        finally
        {

            Reporter.log(message);
        }
    }

    public static void log(String message, LogType type, ITestResult result)
    {
        Reporter.setCurrentTestResult(result);
        log(message, type);
    }

    public static void log(String message)
    {
        log(message, LogType.INFO);
    }

    public static void log(String message, ITestResult result)
    {
        Reporter.setCurrentTestResult(result);
        log(message);
    }

    public static void logUnsuccessfulTest(ITestResult result)
    {
        Reporter.setCurrentTestResult(result);
        if(result.getStatus() != ITestResult.SUCCESS)
        {
            System.out.println("_____________" + result.getStatus());
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            Throwable t = result.getThrowable();
            if(t == null)
            {
                Reporter.log("#ERROR# " + result.getStatus());
            }
            else
            {
                result.getThrowable().printStackTrace(pw);
                Reporter.log("#ERROR# " + sw.toString());
            }
        }
    }
}
