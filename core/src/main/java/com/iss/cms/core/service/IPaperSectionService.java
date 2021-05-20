package com.iss.cms.core.service;

import com.iss.cms.core.domain.PaperSection;
import com.iss.cms.core.exceptions.CMSException;

import java.util.List;

public interface IPaperSectionService {
    List<PaperSection> getPaperSections();

    PaperSection findPaperSectionByID(int id) throws CMSException;

    void addPaperSection(int paperId, int sectionId) throws CMSException;
}
