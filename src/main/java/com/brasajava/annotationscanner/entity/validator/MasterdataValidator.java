package com.brasajava.annotationscanner.entity.validator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

import com.brasajava.annotationscanner.entity.annotation.Masterdata;
import com.brasajava.annotationscanner.entity.annotation.MasterdataHost;

import lombok.Data;

public class MasterdataValidator implements ConstraintValidator<MasterdataHost, Object> {
	private static final String COUNTRY_CODE = "countryCode";
	private boolean isRoot;

	public void initialize(MasterdataHost host) {
		this.isRoot = host.level().equals(MasterdataHost.Level.ROOT);
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		if (isRoot) {
			BeanWrapperImpl bean = new BeanWrapperImpl(obj);
			String countryCode = String.valueOf(bean.getPropertyValue(COUNTRY_CODE));
			List<Master> masters = new ArrayList<>();
			extractMasters(obj, masters, countryCode);
			for (Master m : masters) {
				// call the method to validate the master data list and throw exception if any error
				System.out.println(m);
			}
		}
		return true;
	}

	private void extractMasters(Object obj, List<Master> masters, String countryCode) {
		BeanWrapperImpl bean = new BeanWrapperImpl(obj);
		for (Field f : obj.getClass().getDeclaredFields()) {
			if (f.isAnnotationPresent(Masterdata.class)) {
				masters.add(new Master(f.getAnnotation(Masterdata.class).type(), countryCode,
						(Integer) bean.getPropertyValue(f.getName())));
			} else {
				searchMasterdataHost(bean.getPropertyValue(f.getName()), masters, countryCode);
			}
		}

	}

	private void searchMasterdataHost(Object obj, List<Master> masters, String countryCode) {
		if (obj != null && Arrays.asList(obj.getClass().getInterfaces()).contains(List.class)) {
			System.out.println(obj.getClass().getName() + " is a List");
			if (!((List<?>) obj).isEmpty()
					&& ((List<?>) obj).get(0).getClass().isAnnotationPresent(MasterdataHost.class)) {
				for (Object listObject : (List<?>) obj) {
					extractMasters(listObject, masters, countryCode);
				}
			}
		} else if (obj != null && obj.getClass().isAnnotationPresent(MasterdataHost.class)) {
			System.out.println(obj.getClass().getName() + " is a Masterdata Host");
			extractMasters(obj, masters, countryCode);
		}
	}

	@Data
	class Master {
		private String type;
		private String countryCode;
		private Integer code;
		private String responseMessage;
		private Boolean isValid;

		public Master(String type, String countryCode, Integer code) {
			this.type = type;
			this.countryCode = countryCode;
			this.code = code;
		}

		public String toString() {
			return "Type: " + type + "is valid: " + isValid + " response message: " + responseMessage;
		}
	}

}
