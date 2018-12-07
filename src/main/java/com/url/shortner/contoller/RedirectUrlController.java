package com.url.shortner.contoller;


import com.url.shortner.service.UrlConverterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Controller responsible for redirect the short url request to long url.
 */
@RestController
public class RedirectUrlController {

    private final UrlConverterService urlConverterService;


    public RedirectUrlController(final UrlConverterService urlConverterService) {
        this.urlConverterService = urlConverterService;
    }

    @GetMapping(path = "/{id}")
    public RedirectView getRedirectView(@PathVariable final String id) {
        final String longUrl = urlConverterService.getLongUrl(id);
        final RedirectView redirectView = new RedirectView();
        redirectView.setUrl(longUrl);
        return redirectView;
    }

}
