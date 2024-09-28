package com.query.utility;

import java.util.Comparator;
import com.query.dto.CrmkItemDetailDTO;

public class CrmkItemComparator implements Comparator<CrmkItemDetailDTO> {

	@Override
	public int compare(CrmkItemDetailDTO o1, CrmkItemDetailDTO o2) {
		String o1Compination = (o1.getC() == null || o1.getC().equals("") ? "" : o1.getC()) + (o1.getTone() == null || o1.getTone().equals("") ? "" : o1.getTone());
		String o2Compination = (o2.getC() == null || o2.getC().equals("") ? "" : o2.getC()) + (o2.getTone() == null || o2.getTone().equals("") ? "" : o2.getTone());
		return o1Compination.compareTo(o2Compination);
	}

}
