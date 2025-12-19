package com.example.demo.service;

import com.example.demo.entity.TransferRecord;
import java.util.List;

public interface TransferRecordService {

    TransferRecord createTransfer(TransferRecord record);

    List<TransferRecord> getTransfersByAsset(Long assetId);
}
