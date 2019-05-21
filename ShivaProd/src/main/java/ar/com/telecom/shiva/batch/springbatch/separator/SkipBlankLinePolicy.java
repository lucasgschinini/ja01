package ar.com.telecom.shiva.batch.springbatch.separator;

import org.springframework.batch.item.file.separator.SimpleRecordSeparatorPolicy;

public class SkipBlankLinePolicy extends SimpleRecordSeparatorPolicy {

    @Override
    public boolean isEndOfRecord(String line) {
        if (line.trim().length() == 0) {
            return false;
        }
        return super.isEndOfRecord(line);
    }
    
    @Override
    public String postProcess(String record) {
        if (record == null || record.trim().length() == 0) {
            return null;
        }
        return super.postProcess(record);
    }
}
