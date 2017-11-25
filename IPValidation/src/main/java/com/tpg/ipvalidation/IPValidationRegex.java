/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpg.ipvalidation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * IP validation by regex.
 */
public class IPValidationRegex {

    private static final Pattern IP_REGEX_VALIDATION = Pattern.compile("(\\d{0,3}\\.\\d{0,3}\\.\\d{0,3}\\.\\d{0,3})");

    /**
     * To check {@code address} if it pass regex validation.
     *
     * @param address
     * @return {@code true} if valid IP
     */
    private static boolean isValidIP(String address) {

        Matcher matcher = IP_REGEX_VALIDATION.matcher(address);
        return matcher.matches();
    }

    /**
     * Main method for execution. Filtered IP file is generated in /target
     * folder.
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        File file = new File(IPValidationRegex.class.getClassLoader().getResource("file/IpAddress.txt").getFile());
        List<String> validIpAddresses = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (reader.read() != -1) {
            String ip = reader.readLine();
            if (isValidIP(ip)) {
                System.out.println("validip: " + ip);
                validIpAddresses.add(ip);
            }
        }

        FileWriter writer = new FileWriter(file);
        for (String validIpAddress : validIpAddresses) {
            writer.write(validIpAddress);
            writer.write("\n");
        }
        writer.flush();
    }

}
