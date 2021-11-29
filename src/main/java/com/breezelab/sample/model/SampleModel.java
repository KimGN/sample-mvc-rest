
package com.breezelab.sample.model;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SampleModel {

	private String sampleNo;
	private long sampleSeqNo;
	private LocalDateTime rgstDttm;
	private String sampleYn;
	private String sampleDescription;
	private String result_msg;
}
