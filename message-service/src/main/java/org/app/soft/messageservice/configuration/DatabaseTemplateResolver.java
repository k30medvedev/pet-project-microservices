package org.app.soft.messageservice.configuration;

import org.app.soft.messageservice.model.Template;
import org.app.soft.messageservice.repository.TemplateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import org.thymeleaf.templateresource.ITemplateResource;

import java.util.Collections;
import java.util.Map;

@Component public class DatabaseTemplateResolver extends StringTemplateResolver {
    Logger logger = LoggerFactory.getLogger(DatabaseTemplateResolver.class);

    /*
    this.setResolvablePatterns(Collections.singleton("db-*"))
    Informs Spring Boot to use this Resolver only for the template names that start with db-
    this.setCacheable(true)
    This line makes sure that the template not loaded from the database every time.
    The cache will expire only after specified TTLMs
    Be careful with this setting. That is to say that this may negatively impact application performance.
    this.setCacheTTLMs(5*60*1000L)
    The number of milliseconds for the cache lifecycle. Keep it too small and DB will get overloaded and,
    keep it large and your changes in the DB will not be updated soon enough.
     * */

    final private TemplateRepository templateRepository;

    public DatabaseTemplateResolver(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
        this.setResolvablePatterns(Collections.singleton("db-*"));
        this.setCacheTTLMs(5 * 60 * 1000L);
        this.setCacheable(true);
    }

    @Override protected ITemplateResource computeTemplateResource(IEngineConfiguration configuration,
            String ownerTemplate, String templateName, Map<String, Object> templateResolutionAttributes) {
        logger.debug("Loading template named {} from DB", templateName);
        Template template = templateRepository.findByTemplateName(templateName);
        if (template == null) {
            return null;
        }
        return super.computeTemplateResource(configuration, ownerTemplate, template.getContent(),
                templateResolutionAttributes);
    }
}