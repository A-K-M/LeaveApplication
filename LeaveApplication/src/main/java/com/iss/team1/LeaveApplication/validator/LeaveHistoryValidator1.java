package com.iss.team1.LeaveApplication.validator;
//package com.iss.team1.LeaveApplication.validator;
//
//
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//import com.iss.team1.LeaveApplication.model.LeaveHistory;
//
//public class LeaveHistoryValidator implements Validator{
//
//
//	@Override
//	public boolean supports(Class clazz) {
//		return LeaveHistory.class.equals(clazz);
//	}
//	
//	@Override
//	public void validate(Object target, Errors errors) {
//		// TODO Auto-generated method stub
//		LeaveHistory l=(LeaveHistory) target;
//		if (l.getDescription().isEmpty()) {
//			errors.rejectValue("description", "description", "Description cannot be empty.");
//		}
////		if (l.getStaff().equals(null)) {
////			errors.rejectValue("staff", "error.staff", "Staff cannot be empty.");
////		}
////		if (l.getLeaveType().equals(null)) {
////			errors.rejectValue("leaveType", "error.leaveType", "Leave Type cannot be empty.");
////		}
////		if ((l.getFromDate()!=null && l.getToDate()!=null)&&(l.getFromDate().compareTo(l.getToDate()) > 0)) {
////			errors.reject("toDate", "To date should be later than From date.");
////			errors.rejectValue("toDate", "error.dates", "to date must be > from date");
////	
////		}
////		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.description", "Description is required.");
////		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromDate", "error.fromDate", "From Date is required.");
////		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toDate", "error.toDate", "To Date is required.");
////		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.description", "Description is required.");
////		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.description", "Description is required.");
////		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status", "error.status", "Status is required.");
//	}
//
//}
