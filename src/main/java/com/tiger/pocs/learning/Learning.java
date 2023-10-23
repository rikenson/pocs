package com.tiger.pocs.learning;

import com.tiger.pocs.learning.Classes.CsvModel;
import com.tiger.pocs.learning.Classes.GraphModel;
import com.tiger.pocs.learning.Classes.McpModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Learning {

    private static final List<Object> items = new ArrayList<>();
    static Logger log = LoggerFactory.getLogger(Learning.class);
    private final Function<String, List<GraphModel>> loadGraphData = lObName -> List.of(new GraphModel[]{});
    private final BiFunction<GraphModel, McpModel, CsvModel> mergeGraphAndMcpData =
            (graphModel, mcpModel) -> new CsvModel();
    private final Function<List<CsvModel>, String> generateCsv =
            items -> null;
    Consumer<CsvModel> consume = items::add;

}
