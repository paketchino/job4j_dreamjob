package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.persistence.store.CityStore;
import java.util.List;

@ThreadSafe
@Service
public class CityService {

   private final CityStore cityStore;

    public CityService(CityStore cityStore) {
        this.cityStore = cityStore;
    }

    public List<City> getAllCities() {
        return cityStore.getAllCities();
    }

    public City findById(int id) {
        return cityStore.findById(id);
    }

    public void add(City city) {
      cityStore.add(city);
    }

    public City update(City city) {
        return cityStore.update(city);
    }
}
