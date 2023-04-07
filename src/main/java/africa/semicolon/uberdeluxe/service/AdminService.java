package africa.semicolon.uberdeluxe.service;

import africa.semicolon.uberdeluxe.data.dtos.requests.adminRequest.InviteAdminRequest;
import africa.semicolon.uberdeluxe.data.dtos.response.ApiResponse;

import java.util.Set;

public interface AdminService {
    ApiResponse sendInviteRequests(Set<InviteAdminRequest> inviteAdminRequestList);
}
