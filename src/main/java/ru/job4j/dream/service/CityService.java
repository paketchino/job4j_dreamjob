package ru.job4j.dream.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dream.model.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Service
public class CityService {

    private Map<Integer, City> cities = new ConcurrentHashMap<>();

    private AtomicInteger atomicInteger = new AtomicInteger(4);

    public CityService() {
        cities.put(1, new City(1, "Moscow"));
        cities.put(2, new City(2, "SPB"));
        cities.put(3, new City(3, "Kazan"));
    }

    public List<City> getAllCities() {
        return new ArrayList<>(cities.values());
    }

    public City findById(int id) {
        return cities.get(id);
    }

    public void add(City city) {
        cities.put(atomicInteger.getAndIncrement(), city);
    }

    public City update(City city) {
        return cities.replace(city.getId(), city);
    }
}
