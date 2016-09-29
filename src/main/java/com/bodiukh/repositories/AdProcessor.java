package com.bodiukh.repositories;

import com.bodiukh.domain.Ad;
import com.bodiukh.integration.AdController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class AdProcessor implements ResourceProcessor<Resource<Ad>>{

    @Autowired
    private EntityLinks entityLinks;

    @Override
    public Resource<Ad> process(final Resource<Ad> resource) {
        Ad ad = resource.getContent();
        if (ad.getStatus() == Ad.Status.NEW) {
            resource.add(linkTo(methodOn(AdController.class).
                    publish(ad.getId(), null)).withRel("publishing"));
            resource.add(entityLinks.linkToSingleResource(ad).withRel("update"));
            resource.add(entityLinks.linkToSingleResource(ad).withRel("deletion"));
        }
        if (ad.getStatus() == Ad.Status.PUBLISHED) {
            resource.add(linkTo(methodOn(AdController.class).
                    expire(ad.getId(), null)).withRel("expiration"));
        }

        return resource;
    }
}
