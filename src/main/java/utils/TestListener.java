package utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        String testName = result.getName();
        String path = ScreenshotUtils.captureScreenshot(testName);

        System.out.println("Screenshot saved at: " + path);
    }
}