package com.sleightholme.jeetut.chapter1;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;

@ConversationScoped
public class ConversationStore extends Store{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7643456260344766805L;

	@Inject
	private Conversation conversation;


	@PostConstruct
	@Override
	public void setup(){
		super.setup();
		strings.add("This is the conversations store");
		names.setName("conversation");
		beginConversation();
	}


	public void beginConversation()
	{
		if (conversation.isTransient())
		{
			conversation.begin();
		}
	}

	public void endConversation()
	{
		if (!conversation.isTransient())
		{
			conversation.end();
		}
	}

}
