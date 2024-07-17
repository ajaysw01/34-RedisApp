package com.aj.rest;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aj.binding.Country;

@RestController
public class CountryRestController {

    private final HashOperations<String, String, Country> opsForHash;

    @Autowired
    public CountryRestController(RedisTemplate<String, Country> rt) {
        this.opsForHash = rt.opsForHash();
    }

    @PostMapping("/country")
    public String addCountry(@RequestBody Country country) {
        opsForHash.put("COUNTRIES", String.valueOf(country.getId()), country);
        return "Country added";
    }

    @GetMapping("/countries")
    public Collection<Country> getCountries() {
        Map<String, Country> entries = opsForHash.entries("COUNTRIES");
        return entries.values();
    }
}
