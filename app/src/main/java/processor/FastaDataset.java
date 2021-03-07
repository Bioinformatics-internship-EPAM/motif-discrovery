package processor;

import java.util.HashMap;
import java.util.Map;

public class FastaDataset {

    private Map<String, String> data = new HashMap<>();

    public FastaDataset(){
        // TODO: https://github.com/Bioinformatics-internship-EPAM/motif-discrovery/issues/2
    }

    public void add(String id, String sequence) {
        data.put(id, sequence);
    }

    public Map<String, String> data() {
        return data;
    }
}
