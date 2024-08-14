package com.mghaas.feedbackapp.core.models.item;

import com.mghaas.feedbackapp.core.models.feedback.FeedBackSearchResponseDTO;

import java.util.List;

public record ItemSearchResponseDTO(String id, String name, List<FeedBackSearchResponseDTO> feedbacks) {
}
