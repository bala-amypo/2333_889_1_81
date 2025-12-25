package com.example.demo.service;

import com.example.demo.entity.DisposalRecord;

public interface DisposalRecordService {
    DisposalRecord createDisposal(Long assetId, DisposalRecord record);
    DisposalRecord getDisposal(Long id);
}