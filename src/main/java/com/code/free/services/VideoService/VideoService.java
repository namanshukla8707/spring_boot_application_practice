package com.code.free.services.VideoService;

import java.io.IOException;
import java.util.List;


import com.code.free.requests.VideoRequests.VideoCreateRequest;
import com.code.free.utilities.ApiResult;

public interface VideoService {

     ApiResult<String> uploadVideoFile(List<VideoCreateRequest> videoRequest,Long courseId) throws IOException;

} 