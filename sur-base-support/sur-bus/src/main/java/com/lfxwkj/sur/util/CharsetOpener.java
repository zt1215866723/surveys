package com.lfxwkj.sur.util;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import net.ucanaccess.jdbc.JackcessOpenerInterface;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class CharsetOpener implements JackcessOpenerInterface {
    @Override
    public Database open(File file, String s) throws IOException {
        DatabaseBuilder db = new DatabaseBuilder(file);
        db.setCharset(Charset.forName("gbk"));
        try {
            db.setReadOnly(false);
            return db.open();
        } catch (IOException e) {
            db.setReadOnly(true);
            return db.open();
        }
    }
}
