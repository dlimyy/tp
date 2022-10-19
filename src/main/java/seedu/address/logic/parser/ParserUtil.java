package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.exam.ExamDate;
import seedu.address.model.exam.ExamDescription;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.DeadlineTag;
import seedu.address.model.tag.PriorityTag;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskStatus;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses the {@code moduleCode} String into a {@code ModuleCode} object.
     *
     * @param moduleCode The module code of the module.
     * @return The ModuleCode object created from the moduleCode string.
     * @throws ParseException if the given {@code moduleCode} is not valid.
     */
    public static ModuleCode parseModuleCode(String moduleCode) throws ParseException {
        requireNonNull(moduleCode);
        String trimmedModuleCode = moduleCode.strip();
        if (!ModuleCode.isValidModuleCode(trimmedModuleCode)) {
            throw new ParseException(ModuleCode.MODULE_CODE_CONSTRAINTS);
        }
        return new ModuleCode(trimmedModuleCode);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses the priority status into a PriorityTag.
     *
     * @param priorityTag The priority status added to the tag.
     * @return The priorityTag object containing the priority status.
     * @throws ParseException if the priority status is not valid.
     */
    public static PriorityTag parsePriorityTag(String priorityTag) throws ParseException {
        requireNonNull(priorityTag);
        String trimmedPriorityStatus = priorityTag.strip();
        if (!PriorityTag.isValidTag(priorityTag)) {
            throw new ParseException(PriorityTag.PRIORITY_TAG_CONSTRAINTS);
        }
        return new PriorityTag(trimmedPriorityStatus);
    }

    /**
     * Parses the deadline into a DeadlineTag.
     *
     * @param deadline The deadline which is added to the DeadlineTag.
     * @return The deadlineTag containing the deadline status.
     * @throws ParseException if the deadline is in an invalid format.
     */
    public static DeadlineTag parseDeadlineTag(String deadline) throws ParseException {
        requireNonNull(deadline);
        final LocalDate date;
        //@@author dlimyy-reused
        //Reused from https://stackoverflow.com/questions/32823368/
        //with minor modifications.
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        //@@author
        try {
            date = LocalDate.parse(deadline, dtf);
        } catch (DateTimeParseException dtp) {
            throw new ParseException(DeadlineTag.DEADLINE_TAG_CONSTRAINTS);
        }
        if (!DeadlineTag.isValidDeadline(date)) {
            throw new ParseException(DeadlineTag.DEADLINE_TAG_CONSTRAINTS);
        }
        return new DeadlineTag(date);
    }

    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static TaskDescription parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!TaskDescription.isValidDescription(trimmedDescription)) {
            throw new ParseException(TaskDescription.DESCRIPTION_CONSTRAINTS);
        }
        return new TaskDescription(trimmedDescription);
    }

    /**
     * Parses a {@code String status} into a {@code TaskStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static TaskStatus parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!TaskStatus.isValidStatus(trimmedStatus)) {
            throw new ParseException(TaskStatus.STATUS_CONSTRAINTS);
        }
        return TaskStatus.of(trimmedStatus);
    }

    /**
     * Parses a {@code String description} into a {@code ExamDescription}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static ExamDescription parseExamDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!ExamDescription.isValidDescription(trimmedDescription)) {
            throw new ParseException(ExamDescription.DESCRIPTION_CONSTRAINTS);
        }
        return new ExamDescription(trimmedDescription);
    }

    /**
     * Parses a {@code String examDate} into a {@code ExamDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code examDate} is invalid.
     */
    public static ExamDate parseExamDate(String examDate) throws ParseException {
        requireNonNull(examDate);
        String trimmedDate = examDate.trim();
        if (!ExamDate.isValidDate(trimmedDate)) {
            throw new ParseException(ExamDate.DATE_CONSTRAINTS);
        }
        return new ExamDate(trimmedDate);
    }

}
