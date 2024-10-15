package com.jaspersoft.jasperserver.jaxrs.client.dto.jobs.adapters;

import com.jaspersoft.jasperserver.dto.job.wrappers.ClientExcludeDaysWrapper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class ExcludeDaysXmlAdapter extends XmlAdapter<ClientExcludeDaysWrapper, ArrayList<Calendar>> {
  private DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
  
  public ArrayList<Calendar> unmarshal(ClientExcludeDaysWrapper v) throws Exception {
    ArrayList<Calendar> result = null;
    if (v != null && v.getExcludeDays() != null && !v.getExcludeDays().isEmpty()) {
      result = new ArrayList<>();
      for (String currentCalendarString : v.getExcludeDays()) {
        Date date = this.format.parse(currentCalendarString);
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(date);
        result.add(currentCalendar);
      } 
    } 
    return result;
  }
  
  public ClientExcludeDaysWrapper marshal(ArrayList<Calendar> v) throws Exception {
    ClientExcludeDaysWrapper result = null;
    if (v != null && !v.isEmpty()) {
      List<String> dayStrings = new ArrayList<>();
      for (Calendar currentCalendar : v)
        dayStrings.add(this.format.format(currentCalendar.getTime())); 
      result = new ClientExcludeDaysWrapper(dayStrings);
    } 
    return result;
  }
}