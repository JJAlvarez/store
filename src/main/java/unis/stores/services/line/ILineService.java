package unis.stores.services.line;

import unis.stores.entities.Line;

import java.util.List;

public interface ILineService {

    List<Line> getLines();
    Line getLine(int id);
    Line createLine(String name);
    Line updateLine(int id, String name);
    boolean deleteLine(int id);
    Line searchByName(String name);
}
