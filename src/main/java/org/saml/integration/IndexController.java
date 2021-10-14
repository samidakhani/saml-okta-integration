package org.saml.integration;

import java.util.List;

import org.opensaml.saml2.core.Attribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.providers.ExpiringUsernameAuthenticationToken;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Sami Dakhani Created on Oct 13, 2021
 *
 */
@Controller
public class IndexController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(IndexController.class);

	@RequestMapping("/")
	public String index(final ExpiringUsernameAuthenticationToken samlToken,
			final Model model) {
		SAMLCredential samlCredential = (SAMLCredential) samlToken
				.getCredentials();
		List<Attribute> attributes = samlCredential.getAttributes();
		for (Attribute attribute : attributes) {
			String atributeName = attribute.getName();
			String atributeValue = samlCredential
					.getAttributeAsString(atributeName);
			LOGGER.info("SAML Atribute :  {}={}", atributeName, atributeValue);
		}

		return "index";
	}
}
