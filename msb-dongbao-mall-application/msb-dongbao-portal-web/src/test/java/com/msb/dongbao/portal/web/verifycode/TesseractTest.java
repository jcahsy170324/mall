package com.msb.dongbao.portal.web.verifycode;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

/**
 * @ClassName TesseractTest
 * @Description TODO
 * @Author jincheng
 * @Date 2022/3/13 13:47
 * @Version 1.0
 **/
public class TesseractTest {
    public static void main(String[] args) throws TesseractException {
        ITesseract iTesseract = new Tesseract();
        //语言包加进来
        iTesseract.setDatapath("H:\\project\\Mall\\tessdata");
        iTesseract.setLanguage("eng");

        File fileDir = new File("H:\\data");
        for (File file : fileDir.listFiles()) {
            String s = iTesseract.doOCR(file);
            System.out.println(file.getName()+"识别后的数字是:"+s);
        }
    }
}
