package com.isa.bloodtransfusion.email;

import com.isa.bloodtransfusion.models.User;

public class ComplaintAnswerEmailContext extends AbstractEmailContext {
    @Override
    public <T> void init(T context){
        User customer = (User) context;
        put("firstName", customer.getName());
        setTemplateLocation("emails/complaint-answer");
        setSubject("Complaint answer");
        setFrom("no-reply@blood-transfusion.com");
        setTo(customer.getEmail());
    }

    public void setAdmin(User admin) {
        put("adminName", admin.getName() + " " + admin.getLastName());
    }

    public void setAnswer(String answer) {
        put("answer", answer);
    }

}
