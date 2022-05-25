package ru.job4j.dream.conroller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.dream.model.City;
import ru.job4j.dream.service.CityService;

@ThreadSafe
@Controller
public class CityController {

    private final CityService cityService;


    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/addCity")
    public String addCity(Model model) {
        model.addAttribute("City", new City());
        return "addCity";
    }

    @PostMapping("/createCity")
    public String creatCity(@ModelAttribute City city) {
        cityService.add(city);
        return "redirect:/candidates";
    }

    @GetMapping("/updateCity/{cityId}")
    public String updateCity(Model model, @PathVariable("cityId") int id) {
        model.addAttribute("city", cityService.findById(id));
        return "updateCity";
    }

    @PostMapping("/updateCities")
    public String updateCity(@ModelAttribute City city) {
        cityService.update(city);
        return "redirect:/cities";
    }
}
