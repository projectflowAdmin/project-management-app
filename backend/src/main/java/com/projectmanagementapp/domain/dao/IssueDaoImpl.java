package com.projectmanagementapp.domain.dao;

import com.projectmanagementapp.domain.dao.mapper.IssueMapper;
import com.projectmanagementapp.domain.model.Issue;
import com.projectmanagementapp.dto.IssueRequest;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class IssueDaoImpl implements IssueDao {

    private final IssueMapper issueMapper;

    public IssueDaoImpl(IssueMapper issueMapper) {
        this.issueMapper = issueMapper;
    }

    @Override
    public List<Issue> findAll() {
        return issueMapper.findAll();
    }

    @Override
    public Issue findById(Long id) {
        return issueMapper.findById(id);
    }

    @Override
    public Issue create(IssueRequest request) {
        return issueMapper.create(request);
    }

    @Override
    public Issue update(Long id, IssueRequest request) {
        return issueMapper.update(id, request);
    }

    @Override
    public int deleteById(Long id) {
        return issueMapper.deleteById(id);
    }
}
