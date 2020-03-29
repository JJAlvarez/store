package unis.stores.services.line;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unis.stores.entities.Line;
import unis.stores.repositories.LineRepository;

import java.util.List;

@Service
public class LineService implements ILineService {
    @Autowired
    private LineRepository lineRepository;

    @Override
    public List<Line> getLines() {
        return (List<Line>) lineRepository.findAll();
    }

    @Override
    public Line getLine(int id) {
        return lineRepository.findOne(id);
    }

    @Override
    public Line createLine(String name) {
        Line line = new Line();
        line.setName(name);

        return lineRepository.save(line);
    }

    @Override
    public Line updateLine(int id, String name) {
        if (!lineRepository.exists(id))
            return null;

        Line line = lineRepository.findOne(id);
        line.setName(name);

        return lineRepository.save(line);
    }

    @Override
    public boolean deleteLine(int id) {
        if (!lineRepository.exists(id))
            return false;

        lineRepository.delete(id);
        return true;
    }

    @Override
    public Line searchByName(String name) {
        return lineRepository.findByName(name);
    }
}
