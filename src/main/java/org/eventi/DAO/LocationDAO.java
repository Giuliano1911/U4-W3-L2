package org.eventi.DAO;

import org.eventi.Location;

import java.util.List;

public interface LocationDAO {

    void save (Location location);

    Location getById(Long id);

    void deleteById(Long id);

    List<Location> findAll();
}
