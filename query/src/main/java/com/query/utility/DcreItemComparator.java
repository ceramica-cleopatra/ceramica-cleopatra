package com.query.utility;

import java.util.Comparator;
import com.query.dto.DcreItemDetailDTO;

public class DcreItemComparator implements Comparator<DcreItemDetailDTO> {

	@Override
	public int compare(DcreItemDetailDTO o1, DcreItemDetailDTO o2) {
		String o1Compination = (o1.getC() == null || o1.getC().equals("") ? "" : o1.getC()) + (o1.getTone() == null || o1.getTone().equals("") ? "" : o1.getTone());
		String o2Compination = (o2.getC() == null || o2.getC().equals("") ? "" : o2.getC()) + (o2.getTone() == null || o2.getTone().equals("") ? "" : o2.getTone());
		return o1Compination.compareTo(o2Compination);
	}

}
