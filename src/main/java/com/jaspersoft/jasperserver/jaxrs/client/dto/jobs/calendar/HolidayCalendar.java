/*
 * Copyright (C) 2005 - 2014 Jaspersoft Corporation. All rights  reserved.
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of  the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public  License
 * along with this program.&nbsp; If not, see <http://www.gnu.org/licenses/>.
 */

package com.jaspersoft.jasperserver.jaxrs.client.dto.jobs.calendar;

import com.jaspersoft.jasperserver.dto.job.ClientJobCalendar;
import com.jaspersoft.jasperserver.jaxrs.client.dto.jobs.adapters.ExcludeDaysXmlAdapter;

import java.util.ArrayList;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement(name = "reportJobCalendar")
public class HolidayCalendar extends Calendar {

    public HolidayCalendar() {
        super();
        this.calendarType = ClientJobCalendar.Type.holiday;
    }

    public HolidayCalendar(HolidayCalendar other) {
        super(other);
        this.dataSorted = other.dataSorted;
        this.excludeDays = new ArrayList<java.util.Calendar>();
        if (other.getExcludeDays() != null) {
            for (java.util.Calendar excludeDay : other.getExcludeDays()) {
                this.excludeDays.add(excludeDay);
            }
        }
    }

    private ArrayList<java.util.Calendar> excludeDays = new ArrayList<java.util.Calendar>();
    // true, if excludeDays is sorted
    private Boolean dataSorted;

    @XmlJavaTypeAdapter(ExcludeDaysXmlAdapter.class)
    public ArrayList<java.util.Calendar> getExcludeDays() {
        return excludeDays;
    }

    public HolidayCalendar setExcludeDays(ArrayList<java.util.Calendar> excludeDays) {
        this.excludeDays = excludeDays;
        return this;
    }

    public Boolean getDataSorted() {
        return dataSorted;
    }

    public HolidayCalendar setDataSorted(Boolean dataSorted) {
        this.dataSorted = dataSorted;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        HolidayCalendar that = (HolidayCalendar) o;

        if (dataSorted != null ? !dataSorted.equals(that.dataSorted) : that.dataSorted != null) return false;
        if (excludeDays != null ? !excludeDays.equals(that.excludeDays) : that.excludeDays != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (excludeDays != null ? excludeDays.hashCode() : 0);
        result = 31 * result + (dataSorted != null ? dataSorted.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HolidayCalendar{" +
                "excludeDays=" + excludeDays +
                ", dataSorted=" + dataSorted +
                "} " + super.toString();
    }
}
