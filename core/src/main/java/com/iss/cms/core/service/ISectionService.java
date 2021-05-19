package com.iss.cms.core.service;

import com.iss.cms.core.domain.Section;
import com.iss.cms.core.exceptions.CMSException;

import java.util.List;

public interface ISectionService {
    List<Section> getSections();

    Section findSectionByID(int id) throws CMSException;

    void addSection(String name, int ConferenceId) throws CMSException;
}
