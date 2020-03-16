package com.sim.andon.web.core.sitemesh;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

public class FooterTagRuleBundle implements TagRuleBundle {

    public void cleanUp(State arg0, ContentProperty arg1, SiteMeshContext arg2){
    }

    public void install(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext){
        defaultState.addRule("body_script", new ExportTagToContentRule(contentProperty.getChild("body_script"), false));
    }

}
