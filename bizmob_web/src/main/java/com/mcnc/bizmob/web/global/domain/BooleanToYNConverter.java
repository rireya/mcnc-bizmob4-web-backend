package com.mcnc.bizmob.web.global.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * boolean으로 설정된 모든 Entity의 요소들은 Y/N 컨버터를 거친다.
 */
@Converter(autoApply = true)
public class BooleanToYNConverter implements AttributeConverter<Boolean, String> {

	/**
	 * Entity(boolean) to DB Data(Y/N)
	 */
	@Override
	public String convertToDatabaseColumn(Boolean attribute) {
		return (attribute != null && attribute) ? "Y" : "N";
	}

	/**
	 * DB Data(Y/N) to Entity(boolean)
	 */
	@Override
	public Boolean convertToEntityAttribute(String dbData) {
		return "Y".equals(dbData);
	}
	
}
