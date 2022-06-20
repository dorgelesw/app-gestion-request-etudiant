package com.iuc.requests.dto;

import com.iuc.requests.dao.Staff;
import com.iuc.requests.dao.Student;
import lombok.Data;

@Data
public class RequestDto {
  private Long id;
  private Long requestCode;
  private String requestType;
  private String requestReason;
  private String comment;
  private String requestStatut;
  private Student student;
  private Staff staff;
}
