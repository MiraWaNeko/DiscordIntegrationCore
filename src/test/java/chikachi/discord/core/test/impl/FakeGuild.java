package chikachi.discord.core.test.impl;

import net.dv8tion.jda.client.requests.restaction.pagination.MentionPaginationAction;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.Region;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.managers.AudioManager;
import net.dv8tion.jda.core.managers.GuildController;
import net.dv8tion.jda.core.managers.GuildManager;
import net.dv8tion.jda.core.managers.GuildManagerUpdatable;
import net.dv8tion.jda.core.requests.Request;
import net.dv8tion.jda.core.requests.Response;
import net.dv8tion.jda.core.requests.RestAction;
import net.dv8tion.jda.core.requests.restaction.pagination.AuditLogPaginationAction;
import net.dv8tion.jda.core.utils.cache.MemberCacheView;
import net.dv8tion.jda.core.utils.cache.SnowflakeCacheView;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class FakeGuild implements Guild {
    @Override
    public RestAction<EnumSet<Region>> retrieveRegions() {
        return null;
    }

    @Override
    public String getName() {
        return "FakeGuild";
    }

    @Override
    public String getIconId() {
        return null;
    }

    @Override
    public String getIconUrl() {
        return null;
    }

    @Override
    public Set<String> getFeatures() {
        return null;
    }

    @Override
    public String getSplashId() {
        return null;
    }

    @Override
    public String getSplashUrl() {
        return null;
    }

    @Override
    public RestAction<String> getVanityUrl() {
        return null;
    }

    @Override
    public VoiceChannel getAfkChannel() {
        return null;
    }

    @Override
    public TextChannel getSystemChannel() {
        return null;
    }

    @Override
    public Member getOwner() {
        return null;
    }

    @Override
    public Timeout getAfkTimeout() {
        return null;
    }

    @Override
    public Region getRegion() {
        return null;
    }

    @Override
    public String getRegionRaw() {
        return null;
    }

    @Override
    public boolean isMember(User user) {
        return false;
    }

    @Override
    public Member getSelfMember() {
        return null;
    }

    @Override
    public Member getMember(User user) {
        return new FakeMember(this, (FakeUser) user);
    }

    @Override
    public Member getMemberById(String s) {
        return null;
    }

    @Override
    public Member getMemberById(long l) {
        return null;
    }

    @Override
    public List<Member> getMembers() {
        return null;
    }

    @Override
    public List<Member> getMembersByName(String s, boolean b) {
        return null;
    }

    @Override
    public List<Member> getMembersByNickname(String s, boolean b) {
        return null;
    }

    @Override
    public List<Member> getMembersByEffectiveName(String s, boolean b) {
        return null;
    }

    @Override
    public List<Member> getMembersWithRoles(Role... roles) {
        return null;
    }

    @Override
    public List<Member> getMembersWithRoles(Collection<Role> collection) {
        return null;
    }

    @Override
    public MemberCacheView getMemberCache() {
        return null;
    }

    @Override
    public Category getCategoryById(String id) {
        return null;
    }

    @Override
    public Category getCategoryById(long id) {
        return null;
    }

    @Override
    public List<Category> getCategories() {
        return null;
    }

    @Override
    public List<Category> getCategoriesByName(String name, boolean ignoreCase) {
        return null;
    }

    @Override
    public SnowflakeCacheView<Category> getCategoryCache() {
        return null;
    }

    @Override
    public TextChannel getTextChannelById(String s) {
        return null;
    }

    @Override
    public TextChannel getTextChannelById(long l) {
        return null;
    }

    @Override
    public List<TextChannel> getTextChannels() {
        return null;
    }

    @Override
    public List<TextChannel> getTextChannelsByName(String s, boolean b) {
        return null;
    }

    @Override
    public SnowflakeCacheView<TextChannel> getTextChannelCache() {
        return null;
    }

    @Override
    public VoiceChannel getVoiceChannelById(String s) {
        return null;
    }

    @Override
    public VoiceChannel getVoiceChannelById(long l) {
        return null;
    }

    @Override
    public List<VoiceChannel> getVoiceChannels() {
        return null;
    }

    @Override
    public List<VoiceChannel> getVoiceChannelsByName(String s, boolean b) {
        return null;
    }

    @Override
    public SnowflakeCacheView<VoiceChannel> getVoiceChannelCache() {
        return null;
    }

    @Override
    public Role getRoleById(String s) {
        return null;
    }

    @Override
    public Role getRoleById(long l) {
        return null;
    }

    @Override
    public List<Role> getRoles() {
        return null;
    }

    @Override
    public List<Role> getRolesByName(String s, boolean b) {
        return null;
    }

    @Override
    public SnowflakeCacheView<Role> getRoleCache() {
        return null;
    }

    @Override
    public Emote getEmoteById(String s) {
        return null;
    }

    @Override
    public Emote getEmoteById(long l) {
        return null;
    }

    @Override
    public List<Emote> getEmotes() {
        return null;
    }

    @Override
    public List<Emote> getEmotesByName(String s, boolean b) {
        return null;
    }

    @Override
    public SnowflakeCacheView<Emote> getEmoteCache() {
        return null;
    }

    @Deprecated
    public RestAction<List<User>> getBans() {
        return null;
    }

    @Nonnull
    @Override
    public RestAction<List<Ban>> getBanList() {
        return new RestAction<List<Ban>>(getJDA(), null) {
            @Override
            protected void handleResponse(Response response, Request<List<Ban>> request) {

            }
        };
    }

    @Override
    public RestAction<Integer> getPrunableMemberCount(int i) {
        return null;
    }

    @Override
    public Role getPublicRole() {
        return null;
    }

    @Nullable
    @Override
    public TextChannel getDefaultChannel() {
        return null;
    }

    @Override
    public GuildManager getManager() {
        return null;
    }

    @Override
    public GuildManagerUpdatable getManagerUpdatable() {
        return null;
    }

    @Override
    public GuildController getController() {
        return null;
    }

    @Override
    public MentionPaginationAction getRecentMentions() {
        return null;
    }

    @Override
    public AuditLogPaginationAction getAuditLogs() {
        return null;
    }

    @Override
    public RestAction<Void> leave() {
        return null;
    }

    @Override
    public RestAction<Void> delete() {
        return null;
    }

    @Override
    public RestAction<Void> delete(String s) {
        return null;
    }

    @Override
    public AudioManager getAudioManager() {
        return null;
    }

    @Override
    public JDA getJDA() {
        return null;
    }

    @Override
    public RestAction<List<Invite>> getInvites() {
        return null;
    }

    @Override
    public RestAction<List<Webhook>> getWebhooks() {
        return null;
    }

    @Override
    public List<GuildVoiceState> getVoiceStates() {
        return null;
    }

    @Override
    public VerificationLevel getVerificationLevel() {
        return null;
    }

    @Override
    public NotificationLevel getDefaultNotificationLevel() {
        return null;
    }

    @Override
    public MFALevel getRequiredMFALevel() {
        return null;
    }

    @Override
    public ExplicitContentLevel getExplicitContentLevel() {
        return null;
    }

    @Override
    public boolean checkVerification() {
        return false;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public long getIdLong() {
        return 0;
    }
}
