package com.example.demo.service;

import com.example.demo.entity.DisposalRecord;

import java.util.List;

public interface DisposalRecordService {

    DisposalRecord createDisposal(DisposalRecord record);

    List<DisposalRecord> getAllDisposals();

    List<DisposalRecord> getDisposalsByAsset(Long assetId);
}
