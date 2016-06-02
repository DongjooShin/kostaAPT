package kosta.apt.mapper;

import java.util.List;

import kosta.apt.domain.Property.Property;




public interface PropertyMapper {

	public void APTSaleInsert(Property property);

	public List<Property> APTlist();

}
