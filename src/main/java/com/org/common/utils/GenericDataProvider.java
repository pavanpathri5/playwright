package com.org.common.utils;
import com.org.common.config.Environment;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GenericDataProvider {
    public GenericDataProvider(){
    }

    @DataProvider(
            name="GenericDataProvider",
            parallel = true
    )
    public static Object[][] dataProvider(ITestContext ctx, ITestNGMethod m){
        String filePath=getFilePath(m,ctx);
        String testMethodName = m.getMethodName();
        return JsonUtil.getDataObject(ctx,testMethodName,filePath);
    }

    public static String getMethodName(ITestNGMethod m){
        return m.getMethodName();
    }

    public static String getClassName(ITestContext ctx){
        Class x = ctx.getSuite().getAllMethods().listIterator().next().getRealClass();
//Then just take it as a String
        String name = x.getName().substring(x.getName().lastIndexOf(".")+1);
        return name;
    }

    public static String getFilePath(ITestNGMethod m,ITestContext ctx){
        Environment env=new Environment();
        String testMethodName=getMethodName(m);
        String testClassName=getClassName(ctx);
        System.out.println(testClassName+testMethodName);
        File rootDirectory = new File(System.getProperty("user.dir"));
        final List<File> foundFiles = new ArrayList<>();
        try (Stream<Path> walkStream = Files.walk(rootDirectory.toPath())) {
            walkStream.filter(p -> p.toFile().isFile())
                    .forEach(f -> {
                        if (f.toString().endsWith(testMethodName+".json")) {
                            foundFiles.add(f.toFile());
                        }
                        if (f.toString().endsWith(testClassName+".json")) {
                            foundFiles.add(f.toFile());
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(int i=0;i< foundFiles.size();i++){
            String environment=env.readProperties("env");
            if(foundFiles.get(i).toString().contains(environment)){
                return foundFiles.get(i).toString();
            }
        }
        return "FILE NOT FOUND";
    }
}
