package com.org.common.utils;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.DataProvider;
public class GenericDataProvider {
    public GenericDataProvider(){
    }

    @DataProvider(
            name="GenericDataProvider",
            parallel = true
    )
    public static Object[][] dataProvider(ITestContext ctx, ITestNGMethod m){
        try {
            String filePath=m.getRealClass().getField("filePath").get(new String()).toString();
            String testMethodName = m.getMethodName();
            return JsonUtil.getDataObject(ctx,testMethodName,filePath);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

    }
}
