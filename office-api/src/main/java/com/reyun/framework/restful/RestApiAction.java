package com.reyun.framework.restful;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.knappsack.swagger4springweb.controller.ApiDocumentationController;
import com.reyun.framework.util.PackageUtil;
import com.wordnik.swagger.model.ApiInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Nullable;
import java.util.Set;

/**
 * This is an example of how you might extend the ApiDocumentationController in order to set your own RequestMapping
 * (instead of the default "/api") among other possibilities.  Going this route, you do not necessarily have to define
 * the controller in your servlet context.
 */
@Controller
@RequestMapping(value = "/restapi")
public class RestApiAction extends ApiDocumentationController {

	public RestApiAction() {
		setBaseControllerPackage("com.reyun.framework.restful");
		setBaseModelPackage("com.reyun.framework.model");
		setApiVersion("v1");

		String packages = "com.reyun.*.*.action";
		Set<String> als = PackageUtil.findPackageClass(packages);
		Iterable<String> trans = Iterables.transform(als, new Function<String, String>() {
			@Nullable
			@Override
			public String apply(@Nullable String s) {
				return s.substring(0, s.lastIndexOf("."));
			}
		});
		setAdditionalControllerPackages(Lists.newArrayList(trans));

		packages = "com.reyun.*.*.model";
		als = PackageUtil.findPackageClass(packages);
		trans = Iterables.transform(als, new Function<String, String>() {
			@Nullable
			@Override
			public String apply(@Nullable String s) {
				return s.substring(0, s.lastIndexOf("."));
			}
		});
		setAdditionalModelPackages(Lists.newArrayList(trans));

		setApiInfo(new ApiInfo("Anduin-Office", "",
				"http://office.reyun.com/terms", "http://office.reyun.com/contact", "MIT", ""));
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String documentation() {
		return "documentation";
	}
}
