package kosta.apt.mapper;

import kosta.apt.domain.vote.APTGroup;

public interface AptMapper {
	public APTGroup selectOneAPT(int gno);
	public int updateGroupMainImage(APTGroup apt);
	
}
